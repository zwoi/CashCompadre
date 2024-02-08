import { Role } from "./role";
import { Category } from "./category";

export interface User {
    loginName?: string;
    roles: [Role];
    categories: [Category];
    balance: number;
    restGeld?: number;
}