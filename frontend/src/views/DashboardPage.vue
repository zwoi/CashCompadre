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
import { onMounted, } from "vue";
import { useUser } from '../composables/useUser';
import { Category } from "@/model/category";
import { useCategory } from "@/composables/useCategory";
import Chart from 'chart.js/auto';
const { thisuser, getUserValues, setBalance } = useUser();
const { categories, getCategories } = useCategory();
const text = ref('!');
let myChart; // Um die Diagramminstanz zu speichern

import { addExpenseComp } from "@/composables/addExpenseComp.vue";




function calculateRestGeld() {
  if (thisuser.value && categories.value) {
    let restGeld = thisuser.value.balance;
    for (let i = 0; i < categories.value.length; i++) {
      restGeld -= categories.value[i].limitAmount; // Deduct the category limit
    }
    return restGeld;
  } else {
    console.log('User or categories are not defined');
    console.log(thisuser.value);
    console.log(categories.value);
    return 0;
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
  createChart();
  calculateRestGeld();
});

function generateRandomColor() {
  return `#${Math.floor(Math.random() * 16777215).toString(16)}`;
}

function createChart() {
  const ctx = document.getElementById('myChart').getContext('2d');

  const categoryLabels = categories.value.map(category => category.name);
  const categoryLimits = categories.value.map(category => category.limitAmount);

  const categoryColors = categories.value.map(() => generateRandomColor());

  myChart = new Chart(ctx, {
    type: 'pie',
    data: {
      labels: categoryLabels,
      datasets: [{
        data: categoryLimits,
        backgroundColor: categoryColors,
      }],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
    },
  });
}

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
          <ion-label>Monatliche Einnahmen:</ion-label>
          <ion-label><ion-icon :icon="createOutline" @click="opensetBalanceAlert"></ion-icon></ion-label>
          <h1>{{ thisuser.balance }}</h1>

        </ion-item>

        <ion-item>
          <ion-label>Voraussichtliche monatliche Ersparnisse:</ion-label>
          <h1>{{ calculateRestGeld() }}</h1>
        </ion-item>

        <!-- Weitere Zeilen für zusätzliche Benutzerinformationen -->
      </ion-list>

      <ion-list v-else>
        <ion-item>
          <ion-label>Daten werden geladen</ion-label>
        </ion-item>
      </ion-list>

      <ion-list>
        <ion-item>
        <ion-label>Deine geplante Ausgabenverteilung:</ion-label>
      </ion-item>
      </ion-list>

      <div style="max-width: 400px; margin: auto;">
        <canvas id="myChart"></canvas>
      </div>

      <ion-alert :isOpen="showsetBalanceAlert" @didDismiss="setOpen(false)" header="Update ur Balance!"
        :buttons="alertsetBalanceCategoryButtons" :inputs="alertsetBalanceCategoryInputs">
      </ion-alert>

    </ion-content>
  </ion-page>
</template>