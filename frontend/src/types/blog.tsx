export type Blog = {
    id: number;
    title: string;
    titleDescription: string;
    description: string;
    dateTime: string;
    category: {
        id: number;
        name: string;
        description: string;
        },
        user: {
            id: number;
            name: string;
            email: string;
            birthDate: string;
            urlImage: string;
            registrationDate: string;
        }
}

export type BlogPage = {
    content: Blog[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}