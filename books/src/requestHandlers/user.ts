import { prisma } from '../db';
import { Prisma } from '../generated/prisma/client';
import { HttpError, NotFoundError } from '../error';
import type { Request, Response, NextFunction } from 'express';
import { UserCreationData } from '../validation/user';
import { assert, StructError } from 'superstruct';
import bcrypt, { hash } from 'bcryptjs';
import jwt from "jsonwebtoken";
import { expressjwt, type Request as AuthRequest } from 'express-jwt';

export async function create_one(req: Request, res: Response) {
    assert(req.body, UserCreationData);

    try {
        var user = await prisma.user.create({
            data: {
                username: req.body.username,
                email: req.body.email,
                password: (await hash(req.body.password, 10)).toString()
            }
        });

        const { password, ...userWithoutPassword } = user;
        res.status(201).json({ user: userWithoutPassword });
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2002') {
            throw new HttpError("The email is already used.", 400);
        }
        throw err;
    }
}

export async function connect_one(req: Request, res: Response) {
    /*
    const filter: Prisma.UserWhereInput = {};

    if (req.params.email) {
        filter.email = req.body.email;
    }
    */
    try {
        const user = await prisma.user.findUnique({
            where: {
                email: String(req.body.email)
            }
        });

        if (user) {
            if (await bcrypt.compare(String(req.body.password), user.password)) {
                const token = jwt.sign(
                    { userId: user.id },
                    process.env.JWT_SECRET as string,
                    { expiresIn: "1h" }
                );

                const { password, ...userWithoutPassword } = user;

                res.json({
                    user: userWithoutPassword,
                    token
                });
            }
        } else {
            throw new NotFoundError("User not found.");
        }
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError("User not found");
        }
        throw err;
    }
}

export const auth_client = [
    expressjwt({
        secret: process.env.JWT_SECRET as string,
        algorithms: ['HS256'],
    }),
    async (req: AuthRequest, res: Response, next: NextFunction) => {
        const user = await prisma.user.findUnique({
            where: { id: Number(req.auth?.id) }
        });
        if (user) {
            req.auth = user;
            next();
        } else {
            res.status(401).send('Invalid token');
        }
    }
];
