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
  IonIcon,
  IonFab,
  IonFabButton,
  IonLabel,
  IonAlert,
  IonBackButton,
  alertController
} from "@ionic/vue";

import { ref } from "vue";
import {
  add,
  airplaneOutline,
  createOutline,
  fastFoodOutline,
  homeOutline,
  starOutline,
trashBinOutline
} from 'ionicons/icons';
import { useCategory } from '../composables/useCategory';
import { onMounted } from 'vue';

const text = ref('hello');
const { categories, getCategories, addNewCategory, deleteCategory } = useCategory();

const alertButtons = ['OK'];
const alertInputs = [
  {
    placeholder: 'Category Name',
    attributes: {
      maxlength: 15,
    },
  },
  {
    type: 'number',
    placeholder: 'Limit',
  },
];

onMounted(() => {
  getCategories();
});
let showUpdateAlert=ref(false);
function openUpdateCategoryAlert() {
  document.getElementById('updatecategory-alert')?.click();
}
function setOpen(value: boolean) {
  showUpdateAlert.value = value;
}
function openUpdateAlert() {
  event?.preventDefault();
  setOpen(true);

}
function triggerdeleteFunction(id:number) {
  event?.preventDefault();
  deleteCategory(1)
//funktioniert nicht
  
  
  

}
</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Categories</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-list lines="inset" :inset="true" >
        <ion-item v-for="category in categories" :key="category.id" :router-link="'/tabs/categories/' + category.name">
          <ion-label>{{ category.name }}</ion-label>
          <ion-label>{{ category.limitamount }}Kurwa keine Zahl</ion-label>
          <ion-label v-if="category.name=='Essen'"><ion-icon :icon="fastFoodOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Freizeit'"><ion-icon :icon="airplaneOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Wohnen'"><ion-icon :icon="homeOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Sonstiges'"><ion-icon :icon="starOutline"></ion-icon></ion-label>
          <ion-label ><ion-icon :icon="createOutline" id="updatecategory-alert" @click="openUpdateAlert"></ion-icon></ion-label>
          <ion-label><ion-icon :icon="trashBinOutline" @click="triggerdeleteFunction(category.id)"></ion-icon></ion-label>
          
        </ion-item>
      </ion-list>

      <ion-fab slot="fixed" vertical="bottom" horizontal="end">
        <ion-fab-button id="addcategory-alert" @click="openUpdateAlert">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>

      <ion-alert 
        trigger="addcategory-alert" header="Add a new Category!"
        :buttons="alertButtons"
        :inputs="alertInputs">
      </ion-alert>

      <ion-alert :isOpen="showUpdateAlert " @didDismiss="setOpen(false)"
        trigger="updatecategory-alert" header="Update a Category!"
        :buttons="alertButtons"
        :inputs="alertInputs">
      </ion-alert>

    </ion-content>
  </ion-page>
</template>

<style scoped>
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
}
</style>
