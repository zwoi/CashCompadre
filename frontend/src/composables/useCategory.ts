import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories, getCategory, addCategory, deleteaCategory} from '@/api/categories';
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
    
    const addNewCategory = async (newCategory: Category) => {
        try {
            const response = await addCategory(newCategory);
            getCategories();
            console.log(response);
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

    return { categories, category, getCategories, getOneCategory, addNewCategory,deleteCategory };
}