import { object, string, size, optional, enums } from 'superstruct';

export const CommentCreationData = object({
    content: string()
});

export const CommentUpdateData = object({
    content: string()
});