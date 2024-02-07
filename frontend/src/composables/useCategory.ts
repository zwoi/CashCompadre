import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories, getCategory } from '@/api/categories';
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
    

    return { categories,category, getCategories, getOneCategory };

}