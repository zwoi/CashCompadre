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
  alertController,
} from "@ionic/vue";

import { ref, watch, watchEffect } from "vue"; 
import {
  add,
  airplaneOutline,
  cashOutline,
  createOutline,
  fastFoodOutline,
  homeOutline,
  starOutline,
  trashBinOutline
} from 'ionicons/icons';
import { useCategory } from '../composables/useCategory';
import { onMounted } from 'vue';
import { Category } from "@/model/category";
import { showToast } from '../composables/useToast';

const { categories, getCategories, addNewCategory, deleteCategory, updateCategory } = useCategory();
const CategoryToAdd = ref<Category>();
const CategoryToUpdate = ref<Category>();

CategoryToAdd.value = {
  name: "",
  limitAmount: 0,
};
CategoryToUpdate.value = {
  name: "",
  limitAmount: 0,
  id: 0,
};
let reloadlist = ref(false);
const alertaddCategoryInputs = [
  {
    placeholder: 'Category Name',
    type: 'text',
    name: 'Category Name',
    value: "",
  },
  {
    type: 'number',
    name: 'Limit',
    placeholder: 'Limit',
    value: CategoryToAdd.value.limitAmount,
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
    value: CategoryToUpdate.value.limitAmount,
  },
];

const alertupdateCategoryButtons = [{
  text: 'Action',
  handler: (data) => {
    CategoryToUpdate.value.name = data['Category Name'];
    CategoryToUpdate.value.limitAmount = data.Limit;
    updateCategory(CategoryToUpdate.value as Category, CategoryToUpdate.value.id as number);
    showToast("Category was successfully updated!")
  },
}];

// Kp wieso es rot azeigt aber es funktioniert, nice!
const alertaddCategoryButtons = [{
  text: 'Action',
  handler: async (data) => {
    CategoryToAdd.value.name = data['Category Name'];
    CategoryToAdd.value.limitAmount = data.Limit;

    try {
      await addNewCategory(CategoryToAdd.value as Category);
      getCategories();
      showToast('Category added successfully!');
    } catch (error) {
      console.error('Error adding category:', error);
    }
  },
}];

onMounted(() => {
  getCategories();
});

let showUpdateAlert = ref(false);

function setOpen(value: boolean) {
  showUpdateAlert.value = value;
}
function setthisCategoryToUpdate(c: Category) {
  CategoryToUpdate.value = {
    name: c.name,
    limitAmount: c.limitAmount,
    id: c.id,
  };
}
function openUpdateAlert(category: Category) {
  setthisCategoryToUpdate(category);
  event?.preventDefault();
  setOpen(true);
}

function triggerDeleteFunction(id: number){
  event?.preventDefault();
  if (confirm('Are you sure you want to delete this category?')) {
    deleteCategoryFunction(id);
  }
}

async function deleteCategoryFunction(id: number): Promise<void> {
  try {
    await deleteCategory(id);
    getCategories();
    showToast('Category deleted successfully!');
  } catch (error) {
    console.error('Error deleting category:', error);
  }
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

      <ion-list lines="inset" :inset="true">

        <ion-item class="ion-justify-content-between" v-for="category in categories" :key="category.id"
          :router-link="'/tabs/categories/' + category.name">

          <ion-label>{{ category.name }}</ion-label>
          <!--<ion-label>{{ category.limitamount }}SUM API?</ion-label>-->
          <ion-label v-if="category.name == 'Essen'"><ion-icon :icon="fastFoodOutline"></ion-icon></ion-label>
          <ion-label v-else-if="category.name == 'Freizeit'"><ion-icon :icon="airplaneOutline"></ion-icon></ion-label>
          <ion-label v-else-if="category.name == 'Wohnen'"><ion-icon :icon="homeOutline"></ion-icon></ion-label>
          <ion-label v-else-if="category.name == 'Sonstiges'"><ion-icon :icon="starOutline"></ion-icon></ion-label>
          <ion-label v-else><ion-icon :icon="cashOutline"></ion-icon></ion-label>

          <ion-label><ion-icon :icon="createOutline" @click="openUpdateAlert(category) "></ion-icon></ion-label>
          <ion-label>Limit: {{ category.limitAmount }}</ion-label>
          <ion-label><ion-icon :icon="trashBinOutline" slot="end"
              @click="triggerDeleteFunction(category.id as number)"></ion-icon></ion-label>

        </ion-item>

      </ion-list>

      <ion-fab slot="fixed" vertical="bottom" horizontal="end">
        <ion-fab-button id="addcategory-alert">
          <ion-icon :icon="add"></ion-icon>
        </ion-fab-button>
      </ion-fab>

      <ion-alert trigger="addcategory-alert" header="Add a new Category!" 
        :buttons="alertaddCategoryButtons"
        :inputs="alertaddCategoryInputs">
      </ion-alert>

      <ion-alert :isOpen="showUpdateAlert" @didDismiss="setOpen(false)" header="Update a Category!"
        :buttons="alertupdateCategoryButtons" :inputs="alertupdateCategoryInputs">
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

#seperator {
  --ion-item-justify-content: space-between;
}
</style>
