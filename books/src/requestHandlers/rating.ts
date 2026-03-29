import { prisma } from '../db';
import { Prisma } from '../generated/prisma/client';
import { HttpError, NotFoundError } from '../error';
import type { Request, Response, NextFunction } from 'express';

import { assert, unknown } from 'superstruct';
import { RatingCreationData, RatingUpdateData } from '../validation/rating';

export async function get_all_of_book(req: Request, res: Response) {
    const bookID = Number(req.params.book_id);

    try {
        const ratings = await prisma.rating.findMany({
            where: { bookId: bookID }
        });

        res.json(ratings);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Ratings not found');
        }
        throw err;
    }
}

export async function create_one(req: Request, res: Response) {
    assert(req.body, RatingCreationData);

    const userID = (req as any).auth.id;
    const bookID = Number(req.params.book_id);

    const rating = await prisma.rating.create({
        data: {
            value: req.body.value,
            userId: userID,
            bookId: bookID
        }
    });

    res.status(201).json(rating);
}

export async function update_one(req: Request, res: Response) {
    assert(req.body, RatingUpdateData);

    try {
        const userID = (req as any).auth.id;
        const ratingID = Number(req.params.rating_id);

        const rating = await prisma.rating.update({
            where: {
                id: ratingID,
                userId: userID
            },
            data: req.body
        });

        res.status(200).json(rating);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Rating not found');
        }
        throw err;
    }
}

export async function delete_one(req: Request, res: Response) {
    const ratingID = Number(req.params.rating_id);

    try {
        const userID = (req as any).auth.id;
        await prisma.rating.delete({
            where: {
                id: ratingID,
                userId: userID
            }
        });

        res.sendStatus(204);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Rating not found');
        }
        throw err;
    }
}

export async function get_average_of_book(req: Request, res: Response) {
    const bookID = Number(req.params.book_id);

    const aggregations = await prisma.rating.aggregate({
        _avg: { value: true },
        where: { bookId: bookID }
    });

    res.send("Average value : " + aggregations._avg.value);
}