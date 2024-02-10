import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { addanExpense, getAllExpenses, deleteanExpense } from '@/api/expenses';
import { useCategory } from '../composables/useCategory';
const { categories, getOneCategory } = useCategory();

 
export function useExpenses() {

    const expenses = ref<Expense[]>([]);
    

    const getExpenses = async (categoryName: string) => {
         try {
            expenses.value = await getAllExpenses(categoryName);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        } 
    }
    //error handling machen
    const addExpense = async (e:Expense , id:number, categoryName:string) => {
         try {
            console.log("thisLog:" +categoryName);
             await addanExpense(e,id);
             await getOneCategory(categoryName);
             await getAllExpenses(categoryName);
             getExpenses(categoryName);
             
             console.log(categoryName);
         } catch (error) {
             console.log(error); // FIXME: Errorhandling
         }
     }

     const deleteExpense = async (categoryId:number, expenseId:number, categoryName:string) => {
        try{
            expenses.value = await deleteanExpense(categoryId,expenseId);
            getExpenses(categoryName);
        }catch(error){
            console.log(error);
        }
    }




    return { expenses, getExpenses, addExpense, deleteExpense };

}