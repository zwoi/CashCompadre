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
import { User, categories } from "@/model/user";
import { onMounted, } from "vue";
import { useUser } from '../composables/useUser';
import { Category } from "@/model/category";
import {useCategory} from "@/composables/useCategory";
const { thisuser, getUserValues, setBalance } = useUser();
const{categories, getCategories} = useCategory();
const text = ref('!');
const ExpenseToAdd = ref<Expense>();
ExpenseToAdd.value = {

  category: 1,
  note: 'hello',
  amount: 1,
};


function calculateRestGeld() {
  if (thisuser.value && categories) { // Ensure user.value is not undefined
    let restGeld = thisuser.value.balance;
    
    for (let i = 0; i < categories.value.length; i++) {
      let category = user.categories[i];

      console.log("Categories: "+ categories.value[i].limitamount);
      console.log("Cats: "+category.name)

      restGeld -= categories.value[i].limitamount;
      console.log("Restgeld: " + restGeld);
    }
    console.log("Restgeld: " + restGeld);
    return restGeld;
  } else {
    console.log('User is not defined');
    console.log(thisuser.value);
    console.log(categories.value);

    return 0; // or any default value when user is not defined
  }
}


const UserToUpdate = ref<User>();
  UserToUpdate.value = {


balance: 0,

};
let showsetBalanceAlert = ref(false);

function setOpen(value: boolean) {
  showsetBalanceAlert.value = value;
}

function opensetBalanceAlert() {
  event?.preventDefault();
  setOpen(true);
}

const alertsetBalanceCategoryInputs = [
  {
    type: 'number',
    name: 'balance',
    placeholder: 'Balance',
    balance: 0,
  },
];

const alertsetBalanceCategoryButtons = [{
  text: 'Action',
  handler: (data) => {
    UserToUpdate.value.balance = data.balance;
    setBalance(UserToUpdate.value.balance as number);
    setOpen(false);
  },
}];

onMounted(async () => {
    await getUserValues(); // Await getUserValues() to complete
    await getCategories();
    calculateRestGeld();
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
        <ion-list v-if="thisuser">
        <ion-item>
          <ion-label>Geplante monatliche Ausgaben:</ion-label>
          <ion-text>{{ thisuser.balance }}</ion-text>
          <ion-label><ion-icon :icon="createOutline" @click="opensetBalanceAlert"></ion-icon></ion-label>
        </ion-item>
        <!-- Weitere Zeilen für zusätzliche Benutzerinformationen -->
      </ion-list>
      <ion-list v-else>
        <ion-item>
          <ion-label>Daten werden geladen</ion-label>
        </ion-item>
      </ion-list>

      <ion-alert :isOpen="showsetBalanceAlert" @didDismiss="setOpen(false)" header="Update ur Balance!"
        :buttons="alertsetBalanceCategoryButtons" :inputs="alertsetBalanceCategoryInputs">
      </ion-alert>


    </ion-content>
  </ion-page>
</template>
  
