import { Expense } from "./expense";

export interface Category {
    id?: number;
    name: string;
    limitAmount: number;
    expenses: Expense[];
}