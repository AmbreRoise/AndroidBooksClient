import { object, string, size, optional, enums, integer } from 'superstruct';

export const RatingCreationData = object({
    value: integer()
});

export const RatingUpdateData = object({
    value: integer()
});