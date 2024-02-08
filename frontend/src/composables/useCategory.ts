import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories, getCategory, addCategory, deleteaCategory, updateaCategory} from '@/api/categories'; 
import { Category } from '@/model/category';

export function useCategory(){

    const categories = ref<Category[]>([]);
    const category = ref<Category>();
    
    const getCategories = async () => {
        try {
            categories.value = await getAllCategories();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getOneCategory = async (c:string) => {
        try {
            category.value = await getCategory(c);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
    
    const addNewCategory = async (c: Category) => {
        try {
            
            addCategory(c);
            console.log("Category added");
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const deleteCategory = async (id: number) => {
        try {
            //const response = await deleteCategory(id);
            deleteaCategory(id);
            console.log("Category deleted");
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
    const updateCategory = async (c:Category,id: number) => {
        try {
            //const response = await deleteCategory(id);
            updateaCategory(id,c);
            console.log("Category updated");
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    return { categories, category, getCategories, getOneCategory, addNewCategory,deleteCategory ,updateCategory};
}