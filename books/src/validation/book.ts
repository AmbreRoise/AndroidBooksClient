import { object, string, size, optional, enums, refine, number } from 'superstruct';

export const BookCreationData = object({
    title: size(string(), 1, 50),
    publication_year: optional(refine(number(), 'int', (value) => Number.isInteger(value)))
});

export const BookUpdateData = object({
    title: optional(size(string(), 1, 50)),
    publication_year: optional(number())

});

export const BookGetQueries = object({
    title: optional(string()),
    include: optional(enums(['author'])),
    skip: optional(string()),
    take: optional(string())
});