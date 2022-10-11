export type Blog = {
    id: number;
    title: string;
    titleDescription: string;
    description: string;
    dateTime: string;
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