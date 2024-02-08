<script setup lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonButton,
  IonInput,
  IonFab,
  IonFabButton,
  IonIcon,
  IonAlert,
  IonLabel,
  IonText,
} from "@ionic/vue";

import { createOutline } from "ionicons/icons";

import { ref } from "vue";
import { add } from 'ionicons/icons';
import { useExpenses } from "@/composables/useExpenses";
import { Expense } from "@/model/expense";
import { User } from "@/model/user";
const { expenses, addExpense } = useExpenses();
import { onMounted, } from "vue";
import { useUser } from '../composables/useUser'
import { useCategory } from '../composables/useCategory';
const { user, getUserValues, setBalance, getUserBalance } = useUser();
const { categories, getCategories, addNewCategory, deleteCategory, updateCategory } = useCategory();
const text = ref('!');
const ExpenseToAdd = ref<Expense>();
ExpenseToAdd.value = {

  category: 1,
  note: 'hello',
  amount: 1,
};


const alertButtons = [
  {
    text: 'Action',
    handler: () => addExpense(ExpenseToAdd.value as Expense, 1),
  }

];

const alertInputs = [
  {
    placeholder: 'Category Name',
    name: 'Category Name',
    value: ExpenseToAdd.value.category,
  },
  {
    placeholder: 'Expense Name',
    name: 'Expense Name',
    value: ExpenseToAdd.value.note,
  },
  {
    type: 'number',
    name: 'Amount',
    placeholder: 'Amount',
    value: ExpenseToAdd.value.amount,
  },

];


function calculateRestGeld() {
  if (user.value) { // Ensure user.value is not undefined
    let restGeld = user.value.balance;
    for (let i = 0; i < categories.value.length; i++) {
      restGeld -= categories.value[i].limitamount;
    }
    console.log("Restgeld: " + restGeld);
    return restGeld;
  } else {
    console.log('User is not defined');
    console.log(user.value);
    console.log(categories.value);
    return 0; // or any default value when user is not defined
  }
}


const UserToUpdate = ref<User>();
let showUpdateAlert = ref(false);

function setOpen(value: boolean) {
  showUpdateAlert.value = value;
}

function openUpdateAlert() {
  event?.preventDefault();
  setOpen(true);
}

const alertupdateCategoryInputs = [
  {
    type: 'number',
    name: 'Balance',
    placeholder: 'Balance',
    value: user.value?.balance,
  },
];

const alertupdateCategoryButtons = [{
  text: 'Action',
  handler: (data) => {
    setBalance(data.Balance as number);
    setOpen(false);
  },
}];

onMounted(async () => {
  await getUserValues();
  getCategories();
});




</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Dashboard</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-list v-if="user">
        <ion-item>
          <ion-label>Geplante monatliche Ausgaben:</ion-label>
          <ion-text>{{ user.balance }}</ion-text>
          <h1>{{ user.balance }}</h1>
          <ion-label><ion-icon :icon="createOutline" @click="openUpdateAlert"></ion-icon></ion-label>
        </ion-item>
        <!-- Weitere Zeilen für zusätzliche Benutzerinformationen -->
      </ion-list>
      <ion-list v-else>
        <ion-item>
          <ion-label>Daten werden geladen</ion-label>
        </ion-item>
      </ion-list>

      <ion-alert :isOpen="showUpdateAlert" @didDismiss="setOpen(false)" header="Update a Category!"
        :buttons="alertupdateCategoryButtons" :inputs="alertupdateCategoryInputs">
      </ion-alert>


    </ion-content>
  </ion-page>
</template>
  
