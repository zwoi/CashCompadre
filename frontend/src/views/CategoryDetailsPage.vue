<script setup lang="ts">


import { useRoute } from 'vue-router';
import { IonPage, IonButton, IonHeader, IonItem, IonLabel, IonTitle, IonContent, IonToolbar, IonFab, IonFabButton, IonList, IonBackButton, IonButtons, IonAlert, IonIcon } from '@ionic/vue';
import { useExpenses } from "@/composables/useExpenses";
import { useCategory } from "@/composables/useCategory";
import { onMounted, ref, watchEffect } from "vue";
import {
    add,
    airplaneOutline,
    createOutline,
    fastFoodOutline,
    homeOutline,
    starOutline,
    trashBinOutline,
    caretBack
} from 'ionicons/icons';
import { Category } from "@/model/category";
import { Expense } from "@/model/expense";
import { showToast } from '../composables/useToast';

const route = useRoute();
console.log(route.params.id);

const categorytitle = route.params.id as string;
const { categories, category, getOneCategory, getCategories } = useCategory();
const { expenses, addExpense, getExpenses, deleteExpense } = useExpenses();

const ExpenseToAdd = ref<Expense>();
const ExpenseToUpdate = ref<Expense>();
const thiscategory = ref<Category>();

ExpenseToAdd.value = {
    note: "",
    amount: 0,
};

const alertaddExpenseInputs = [
    {
        placeholder: 'Expense Note',
        type: 'text',
        name: 'Expense Note',
        value: "",
    },
    {
        type: 'number',
        name: 'amount',
        placeholder: 'Amount',
        value: ExpenseToAdd.value.amount,
    },
];

const alertaddExpensesButtons = [{
    text: 'Action',
    handler: (data) => {
        ExpenseToAdd.value.note = data['Expense Note'];
        ExpenseToAdd.value.amount = data.amount;
        addExpense(ExpenseToAdd.value as Expense, category.value?.id, categorytitle); //addCategoryID
        getExpenses(category.value?.id);
        showToast("Expense was successfully added!");
    },
}];

function triggerDeleteFunction(cateforyId: number, expenseId: number): void {
    if (confirm('Are you sure you want to delete this expense?')) {
        deleteExpense(cateforyId, expenseId, categorytitle);
        showToast("Expense was successfully deleted!")
    }
}


onMounted(() => {
    getOneCategory(categorytitle);
    sumExpenses();
    getExpenses(categorytitle);
});
const i=1;
while(i >0){
    getExpenses(categorytitle);
}
let totalAmount = 0;

watchEffect(() => {
    // Grundsätzlich um die totale Summe zu berechnen warum watchEffect??
    if (category && category.value && category.value.expenses) {
        totalAmount = category.value.expenses.reduce((total, expense) => {
            if (expense.amount !== undefined) {
                return total + expense.amount;
            }
            return total;
        }, 0);
    }
});
//Summe alles expenses hier in category
function sumExpenses() {
    let sum = 0;
    if (category.value) {
        for (let i = 0; i < (category.value.expenses).length; i++) {
            sum += category.value.expenses[i].amount as number;

        }

    }
    return sum;
}



</script>

<template>
    <ion-page>

        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button :icon="caretBack"></ion-back-button>
                </ion-buttons>

                <ion-title>{{ categorytitle }}</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <ion-list v-if="category">
                <ion-item v-for="expense in category.expenses" :key="expense.id">
                    <ion-label>{{ expense.note }}</ion-label>
                    <ion-label>{{ expense.amount }}</ion-label>
                    <ion-label><ion-icon :icon="trashBinOutline" slot="end"
                            @click="triggerDeleteFunction(category.id as number, expense.id as number)"></ion-icon></ion-label>
                </ion-item>
                <!-- Anzeigen des Gesamtbetrags als zusätzlicher Eintrag -->
                <!-- wenn leer kein EIntrag -->
                <ion-item>
                    <ion-label>Total</ion-label>
                    <ion-label>{{ totalAmount }}</ion-label>
                    <ion-label>Limit: {{ category.limitAmount }}</ion-label>
                    <ion-label>
                        Differenz: <span
                            :class="{ 'green-text': (category.limitAmount - totalAmount) >= 0, 'red-text': (category.limitAmount - totalAmount) < 0 }">
                            {{ category.limitAmount - totalAmount }}
                        </span>
                    </ion-label>


                </ion-item>
            </ion-list>

            <ion-fab slot="fixed" vertical="bottom" horizontal="end">
                <ion-fab-button id="addexpense-alert">
                    <ion-icon :icon="add"></ion-icon>
                </ion-fab-button>
            </ion-fab>

            <ion-alert trigger="addexpense-alert" header="Add a new Expense!" :buttons="alertaddExpensesButtons"
                :inputs="alertaddExpenseInputs">
            </ion-alert>

        </ion-content>
    </ion-page>
</template>

<style scoped>
/* In Ihrem Stylesheet */
.green-text {
    color: green;
}

.red-text {
    color: red;
}

ion-alert {
    --color: white;
}

ion-alert .inputs {
    --color: white;
}

ion-alert .input-wrapper {
    --color: white;
}

ion-list {
    --ion-item-background: #006e0000;

}

ion-item {
    --ion-item-background: #b3b3b3a4;
    -moz-box-align: stretch;
}

#seperator {
    --ion-item-justify-content: space-between;
}
</style>