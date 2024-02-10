import { getToken, loguserout } from '@/api/auth';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export function useLogin() {

    const username = ref("");
    const password = ref("");
    const router = useRouter();

    const login = async () => {
        try {
            const token = await getToken(username.value, password.value);
            // TODO: do something meaningful with the token :-)
            //console.log(token)
            router.push('/tabs/dashboard')
        } catch (error) {
            console.log(error)
        }
    }
    const logout = async () => {
        try{
            loguserout();
            console.log("logged out");
            
        }catch (error){
            console.log(error);
        }
        router.push('/login')
    }

    return {
        username,
        password,
        login,
        logout
    }
}