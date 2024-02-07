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
  IonNote,
  alertController,} from "@ionic/vue";

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
import { Category } from "@/model/category";
import { addCategory } from "@/api/categories";
const CategoryToAdd = ref<Category>();
const CategoryToUpdate = ref<Category>();
CategoryToAdd.value = {
  name: 'hello',
  limitamount: 0,
};
CategoryToUpdate.value={
  name: "",
  limitamount: 0,
};


const { categories, getCategories, addNewCategory, deleteCategory, updateCategory } = useCategory();

const alertaddCategoryButtons = [{
          text: 'Action',
          handler: () => addCategory(CategoryToAdd.value as Category),
            }];
const alertaddCategoryInputs = [
  {
    placeholder: 'Category Name',
    name: 'Category Name',
    value: CategoryToAdd.value.name,
  },
  {
    type: 'number',
    name: 'Limit',
    placeholder: 'Limit',
    value: CategoryToAdd.value.limitamount,
  },
];
const alertupdateCategoryInputs = [
{
    placeholder: 'Category Name',
    name: 'Category Name',
    value: CategoryToUpdate.value.name,
  },
  {
    type: 'number',
    name: 'Limit',
    placeholder: 'Limit',
    value: CategoryToUpdate.value.limitamount,
  },
];
const alertupdateCategoryButtons = [{
          text: 'Action',
          handler: () => updateCategory(CategoryToUpdate.value as Category,1),
            }];

onMounted(() => {
  getCategories();
});
let showUpdateAlert=ref(false);

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

      <ion-list lines="inset" :inset="true"  >
        
        <ion-item class="ion-justify-content-between" v-for="category in categories" :key="category.id" :router-link="'/tabs/categories/' + category.name">
        
          <ion-label>{{ category.name }}</ion-label>
          <ion-label>{{ category.limitamount }}SUM API?</ion-label>
          <ion-label v-if="category.name=='Essen'"><ion-icon :icon="fastFoodOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Freizeit'"><ion-icon :icon="airplaneOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Wohnen'"><ion-icon :icon="homeOutline"></ion-icon></ion-label>
          <ion-label v-if="category.name=='Sonstiges'"><ion-icon :icon="starOutline"></ion-icon></ion-label>
          <ion-label ><ion-icon :icon="createOutline"  @click="openUpdateAlert"></ion-icon></ion-label>
          <ion-label><ion-icon :icon="trashBinOutline" slot="end" @click="triggerdeleteFunction(category.id as number )"></ion-icon></ion-label>
        
        </ion-item>
      
      </ion-list>

      <ion-fab slot="fixed" vertical="bottom" horizontal="end">
        <ion-fab-button id="addcategory-alert" >
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>

      <ion-alert 
        trigger="addcategory-alert" header="Add a new Category!"
        :buttons="alertaddCategoryButtons"
        :inputs="alertaddCategoryInputs">
      </ion-alert>

      <ion-alert :isOpen="showUpdateAlert " @didDismiss="setOpen(false)"
        header="Update a Category!"
        :buttons="alertupdateCategoryButtons"
        :inputs="alertupdateCategoryInputs">
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
  -moz-box-align: stretch;
}
#seperator{
  --ion-item-justify-content: space-between;
}
</style>
