import { Expense } from "./expense";

export interface Category {
    id?: number;
    name: string;
    limitamount: number;
    expenses: Expense[];
}