import { ref } from 'vue';
import { Expense } from '@/model/expense';
import { getAllCategories } from '@/api/categories';
import { Category } from '@/model/category';

export function useCategory(){

    const categories = ref<Category[]>([]);
    
    
    const getCategories = async () => {
        try {
            categories.value = await getAllCategories();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
    

    return { categories, getCategories };

}