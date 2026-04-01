import { prisma } from '../src/db'

const tags = [
    { name: "Fantasy" },
    { name: "Science Fiction" },
    { name: "Classic" },
    { name: "Adventure" },
    { name: "Horror" },
    { name: "Philosophy" },
    { name: "Drama" },
    { name: "Poetry" }
];


const authors = [
    {
        firstname: 'George',
        lastname: 'Orwell',
        books: {
            create: [
                {
                    title: '1984',
                    publication_year: 1949,
                    tags: { connect: [{ name: "Classic" }, { name: "Philosophy" }] }
                },
                {
                    title: 'Animal Farm',
                    publication_year: 1945,
                    tags: { connect: [{ name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Mary',
        lastname: 'Shelley',
        books: {
            create: [
                {
                    title: 'Frankenstein',
                    publication_year: 1818,
                    tags: { connect: [{ name: "Classic" }, { name: "Horror" }] }
                },
                {
                    title: 'The Last Man',
                    publication_year: 1826,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Isaac',
        lastname: 'Asimov',
        books: {
            create: [
                {
                    title: 'Foundation',
                    publication_year: 1951,
                    tags: { connect: [{ name: "Science Fiction" }] }
                },
                {
                    title: 'I, Robot',
                    publication_year: 1950,
                    tags: { connect: [{ name: "Science Fiction" }] }
                },
                {
                    title: 'The Caves of Steel',
                    publication_year: 1953,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Agatha',
        lastname: 'Christie',
        books: {
            create: [
                {
                    title: 'Murder on the Orient Express',
                    publication_year: 1934,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                },
                {
                    title: 'And Then There Were None',
                    publication_year: 1939,
                    tags: { connect: [{ name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Jules',
        lastname: 'Verne',
        books: {
            create: [
                {
                    title: 'Twenty Thousand Leagues Under the Sea',
                    publication_year: 1870,
                    tags: { connect: [{ name: "Adventure" }, { name: "Science Fiction" }] }
                },
                {
                    title: 'Journey to the Center of the Earth',
                    publication_year: 1864,
                    tags: { connect: [{ name: "Adventure" }] }
                },
                {
                    title: 'Around the World in Eighty Days',
                    publication_year: 1872,
                    tags: { connect: [{ name: "Adventure" }] }
                }
            ]
        }
    },
    {
        firstname: 'Arthur C.',
        lastname: 'Clarke',
        books: {
            create: [
                {
                    title: '2001: A Space Odyssey',
                    publication_year: 1968,
                    tags: { connect: [{ name: "Science Fiction" }] }
                },
                {
                    title: 'Childhood’s End',
                    publication_year: 1953,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Philip K.',
        lastname: 'Dick',
        books: {
            create: [
                {
                    title: 'Do Androids Dream of Electric Sheep?',
                    publication_year: 1968,
                    tags: { connect: [{ name: "Science Fiction" }] }
                },
                {
                    title: 'The Man in the High Castle',
                    publication_year: 1962,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Ray',
        lastname: 'Bradbury',
        books: {
            create: [
                {
                    title: 'Fahrenheit 451',
                    publication_year: 1953,
                    tags: { connect: [{ name: "Science Fiction" }, { name: "Classic" }] }
                },
                {
                    title: 'The Martian Chronicles',
                    publication_year: 1950,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Leo',
        lastname: 'Tolstoy',
        books: {
            create: [
                {
                    title: 'War and Peace',
                    publication_year: 1869,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                },
                {
                    title: 'Anna Karenina',
                    publication_year: 1877,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                }
            ]
        }
    },
    {
        firstname: 'Fyodor',
        lastname: 'Dostoevsky',
        books: {
            create: [
                {
                    title: 'Crime and Punishment',
                    publication_year: 1866,
                    tags: { connect: [{ name: "Classic" }, { name: "Philosophy" }] }
                },
                {
                    title: 'The Brothers Karamazov',
                    publication_year: 1880,
                    tags: { connect: [{ name: "Classic" }, { name: "Philosophy" }] }
                }
            ]
        }
    },
    {
        firstname: 'Herman',
        lastname: 'Melville',
        books: {
            create: [
                {
                    title: 'Moby-Dick',
                    publication_year: 1851,
                    tags: { connect: [{ name: "Adventure" }, { name: "Classic" }] }
                },
                {
                    title: 'Billy Budd',
                    publication_year: 1924,
                    tags: { connect: [{ name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Virginia',
        lastname: 'Woolf',
        books: {
            create: [
                {
                    title: 'Mrs Dalloway',
                    publication_year: 1925,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                },
                {
                    title: 'To the Lighthouse',
                    publication_year: 1927,
                    tags: { connect: [{ name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Franz',
        lastname: 'Kafka',
        books: {
            create: [
                {
                    title: 'The Metamorphosis',
                    publication_year: 1915,
                    tags: { connect: [{ name: "Classic" }, { name: "Philosophy" }] }
                },
                {
                    title: 'The Trial',
                    publication_year: 1925,
                    tags: { connect: [{ name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Gabriel',
        lastname: 'García Márquez',
        books: {
            create: [
                {
                    title: 'One Hundred Years of Solitude',
                    publication_year: 1967,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                },
                {
                    title: 'Love in the Time of Cholera',
                    publication_year: 1985,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                }
            ]
        }
    },
    {
        firstname: 'Haruki',
        lastname: 'Murakami',
        books: {
            create: [
                {
                    title: 'Kafka on the Shore',
                    publication_year: 2002,
                    tags: { connect: [{ name: "Science Fiction" }, { name: "Philosophy" }] }
                },
                {
                    title: 'Norwegian Wood',
                    publication_year: 1987,
                    tags: { connect: [{ name: "Drama" }] }
                },
                {
                    title: '1Q84',
                    publication_year: 2009,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'J.R.R.',
        lastname: 'Tolkien',
        books: {
            create: [
                {
                    title: 'The Hobbit',
                    publication_year: 1937,
                    tags: { connect: [{ name: "Fantasy" }, { name: "Adventure" }] }
                },
                {
                    title: 'The Lord of the Rings',
                    publication_year: 1954,
                    tags: { connect: [{ name: "Fantasy" }] }
                }
            ]
        }
    },
    {
        firstname: 'C.S.',
        lastname: 'Lewis',
        books: {
            create: [
                {
                    title: 'The Chronicles of Narnia',
                    publication_year: 1950,
                    tags: { connect: [{ name: "Fantasy" }, { name: "Adventure" }] }
                }
            ]
        }
    },
    {
        firstname: 'H.G.',
        lastname: 'Wells',
        books: {
            create: [
                {
                    title: 'The War of the Worlds',
                    publication_year: 1898,
                    tags: { connect: [{ name: "Science Fiction" }] }
                },
                {
                    title: 'The Time Machine',
                    publication_year: 1895,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Bram',
        lastname: 'Stoker',
        books: {
            create: [
                {
                    title: 'Dracula',
                    publication_year: 1897,
                    tags: { connect: [{ name: "Horror" }, { name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Aldous',
        lastname: 'Huxley',
        books: {
            create: [
                {
                    title: 'Brave New World',
                    publication_year: 1932,
                    tags: { connect: [{ name: "Science Fiction" }, { name: "Philosophy" }] }
                }
            ]
        }
    },
    {
        firstname: 'J.D.',
        lastname: 'Salinger',
        books: {
            create: [
                {
                    title: 'The Catcher in the Rye',
                    publication_year: 1951,
                    tags: { connect: [{ name: "Classic" }, { name: "Drama" }] }
                }
            ]
        }
    },
    {
        firstname: 'H.P.',
        lastname: 'Lovecraft',
        books: {
            create: [
                {
                    title: 'The Call of Cthulhu',
                    publication_year: 1928,
                    tags: { connect: [{ name: "Horror" }] }
                }
            ]
        }
    },
    {
        firstname: 'Ursula K.',
        lastname: 'Le Guin',
        books: {
            create: [
                {
                    title: 'A Wizard of Earthsea',
                    publication_year: 1968,
                    tags: { connect: [{ name: "Fantasy" }] }
                },
                {
                    title: 'The Left Hand of Darkness',
                    publication_year: 1969,
                    tags: { connect: [{ name: "Science Fiction" }] }
                }
            ]
        }
    },
    {
        firstname: 'Toni',
        lastname: 'Morrison',
        books: {
            create: [
                {
                    title: 'Beloved',
                    publication_year: 1987,
                    tags: { connect: [{ name: "Drama" }, { name: "Classic" }] }
                }
            ]
        }
    },
    {
        firstname: 'Margaret',
        lastname: 'Atwood',
        books: {
            create: [
                {
                    title: 'The Handmaid’s Tale',
                    publication_year: 1985,
                    tags: { connect: [{ name: "Science Fiction" }, { name: "Drama" }] }
                }
            ]
        }
    },
    {
        firstname: 'Neil',
        lastname: 'Gaiman',
        books: {
            create: [
                {
                    title: 'American Gods',
                    publication_year: 2001,
                    tags: { connect: [{ name: "Fantasy" }] }
                }
            ]
        }
    },
    {
        firstname: 'Stephen',
        lastname: 'King',
        books: {
            create: [
                {
                    title: 'The Shining',
                    publication_year: 1977,
                    tags: { connect: [{ name: "Horror" }] }
                },
                {
                    title: 'It',
                    publication_year: 1986,
                    tags: { connect: [{ name: "Horror" }] }
                }
            ]
        }
    },
    {
        firstname: 'J.K.',
        lastname: 'Rowling',
        books: {
            create: [
                {
                    title: 'Harry Potter and the Philosopher’s Stone',
                    publication_year: 1997,
                    tags: { connect: [{ name: "Fantasy" }] }
                }
            ]
        }
    },
    {
        firstname: 'Alexandre',
        lastname: 'Dumas',
        books: {
            create: [
                {
                    title: 'The Count of Monte Cristo',
                    publication_year: 1844,
                    tags: { connect: [{ name: "Adventure" }, { name: "Classic" }] }
                },
                {
                    title: 'The Three Musketeers',
                    publication_year: 1844,
                    tags: { connect: [{ name: "Adventure" }] }
                }
            ]
        }
    },
    {
        firstname: 'Emily',
        lastname: 'Dickinson',
        books: {
            create: [
                {
                    title: 'The complete poems',
                    publication_year: 1955,
                    tags: { connect: [{ name: "Classic" }, { name: "Poetry" }] }
                }
            ]
        }
    }
];



async function main() {
    await prisma.rating.deleteMany();
    await prisma.comment.deleteMany();
    await prisma.book.deleteMany();
    await prisma.author.deleteMany();
    await prisma.tag.deleteMany();

    await prisma.$executeRaw`DELETE FROM sqlite_sequence`;

    for (const tag of tags) {
        await prisma.tag.create({ data: tag });
    }

    const createdAuthors = [];
    for (const author of authors) {
        const created = await prisma.author.create({
            data: author,
            include: { books: true }
        });
        createdAuthors.push(created);
    }

    const bookMap: Record<string, number> = {};
    for (const author of createdAuthors) {
        for (const book of author.books) {
            bookMap[book.title] = book.id;
        }
    }

    const commentsData = [
        {
            content: "Un livre incroyable, j'ai adoré !", bookId: bookMap["1984"]!, userName: "Alice"
        },
        {
            content: "Très intéressant mais un peu long.", bookId: bookMap["Foundation"]!, userName: "Alice"
        },
        {
            content: "Pas mon style, mais bien écrit.", bookId: bookMap["Journey to the Center of the Earth"]!, userName: "Bob"
        },
        {
            content: "Chef d'œuvre absolu.", bookId: bookMap["War and Peace"]!, userName: "Charlie"
        }
    ];

    const ratingsData = [
        {
            value: 5, bookId: bookMap["1984"]!, userName: "Alice"
        },
        {
            value: 4, bookId: bookMap["Foundation"]!, userName: "Alice"
        },
        {
            value: 3, bookId: bookMap["Journey to the Center of the Earth"]!, userName: "Bob"
        },
        {
            value: 5, bookId: bookMap["Animal Farm"]!, userName: "Bob"
        },
        {
            value: 5, bookId: bookMap["War and Peace"]!, userName: "Charlie"
        },
        {
            value: 4, bookId: bookMap["Do Androids Dream of Electric Sheep?"]!, userName: "Charlie"
        }
    ];

    for (const comment of commentsData) {
        await prisma.comment.create({ data: comment });
    }

    for (const rating of ratingsData) {
        await prisma.rating.create({ data: rating });
    }
}

main()
    .then(async () => {
        await prisma.$disconnect();
    })
    .catch(async (e) => {
        console.error(e);
        await prisma.$disconnect();
        process.exit(1);
    });

