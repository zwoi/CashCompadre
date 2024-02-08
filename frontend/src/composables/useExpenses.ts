import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { addanExpense, getAllExpenses, deleteanExpense } from '@/api/expenses';
 
export function useExpenses() {

    const expenses = ref<Expense[]>([]);
    const newExpense = ref<Expense>();

    const getExpenses = async (categoryName: string) => {
         try {
            expenses.value = await getAllExpenses(categoryName);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        } 
    }
    //error handling machen
    const addExpense = async (e:Expense , id:number) => {
         try {
             expenses.value = await addanExpense(e,id);
         } catch (error) {
             console.log(error); // FIXME: Errorhandling
         }
     }

     const deleteExpense = async (categoryId:number, expenseId:number) => {
        try{
            expenses.value = await deleteanExpense(categoryId,expenseId);
        }catch(error){
            console.log(error);
        }
    }




    return { expenses, getExpenses, addExpense, deleteExpense };

}