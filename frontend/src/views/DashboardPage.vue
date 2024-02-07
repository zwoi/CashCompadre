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
} from "@ionic/vue";

import { ref } from "vue";
import { add } from 'ionicons/icons';
import { useExpenses } from "@/composables/useExpenses";
import { Expense } from "@/model/expense";
const {expenses, addExpense} = useExpenses();
const text = ref('!');
const ExpenseToAdd=ref<Expense>();
ExpenseToAdd.value = {
  
  category: 1,
  note: 'hello',
  amount: 1,
};

const alertButtons = [
        {
          text: 'Action',
          handler: () => addExpense(ExpenseToAdd.value as Expense,1),
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
    placeholder: 'Amount',
    value: ExpenseToAdd.value.amount,
  },
  
];
</script>

<template>
  <ion-page>
  <ion-content>
    <ion-header>
        <ion-toolbar>
            <ion-title>Dashboard</ion-title>
        </ion-toolbar>
    </ion-header>
    <ion-fab slot="fixed" vertical="bottom" horizontal="end"> 
    <ion-fab-button color:primary id="present-alert">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>
      <ion-alert trigger="present-alert" header="Please enter your info" :buttons="alertButtons"
        :inputs="alertInputs"></ion-alert>
    </ion-content>
  
</ion-page>
</template>
  
