import { prisma } from '../db';
import { Prisma } from '../generated/prisma/client';
import { HttpError, NotFoundError } from '../error';
import type { Request, Response, NextFunction } from 'express';

import { assert } from 'superstruct';
import { CommentCreationData, CommentUpdateData } from '../validation/comment';

export async function get_all_of_book(req: Request, res: Response) {
    const bookId = Number(req.params.book_id);

    try {
        const comments = await prisma.comment.findMany({
            where: {
                bookId: bookId
            }
        });

        res.json(comments);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Comment not found');
        }
        throw err;
    }
}

export async function create_one(req: Request, res: Response) {
    assert(req.body, CommentCreationData);

    const bookID = Number(req.params.book_id);
    const userID = (req as any).auth.id;

    var comment = await prisma.comment.create({
        data: {
            content: req.body.content,
            bookId: bookID,
            userId: userID
        }
    });

    res.status(201).json(comment);
}

export async function update_one(req: Request, res: Response) {
    assert(req.body, CommentUpdateData);

    try {
        const commentID = Number(req.params.comment_id);
        const userID = (req as any).auth.id;
        const comment = await prisma.comment.update({
            where: {
                id: commentID,
                userId: userID
            },
            data: req.body
        });

        res.status(200).json(comment);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Comment not found');
        }
        throw err;
    }
}

export async function delete_one(req: Request, res: Response) {
    const commentID = Number(req.params.comment_id);

    try {
        const userID = (req as any).auth.id;
        await prisma.comment.delete({
            where: {
                id: commentID,
                userId: userID
            }
        });

        res.sendStatus(204);
    }
    catch (err: unknown) {
        if (err instanceof Prisma.PrismaClientKnownRequestError && err.code === 'P2025') {
            throw new NotFoundError('Comment not found');
        }
        throw err;
    }
}