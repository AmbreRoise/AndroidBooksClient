import { object, string, optional, number } from 'superstruct';

export const RatingCreationData = object({
    value: number(),
    userName: string()
});

export const RatingUpdateData = object({
    value: optional(number()),
    userName: optional(string())
});