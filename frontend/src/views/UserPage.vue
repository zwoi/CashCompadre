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
  IonLabel,
  IonText
} from "@ionic/vue";
import { useLogin } from "../composables/useLogin";
import { onMounted, ref } from "vue";
import { getUser } from "@/api/users";
import { useUser } from '../composables/useUser'
import { User } from "@/model/user";
import { useCategory } from "@/composables/useCategory";
const { getCategories } = useCategory();
const { thisuser, getUserValues, setBalance } = useUser();
const { username, password, logout } = useLogin();
const text = ref('!');

onMounted(() => {
  getUserValues();
  getCategories();
  sumlimitallCategories();
});
const Limitall = sumlimitallCategories();
function sumlimitallCategories(){
  let sum=0;
  
  if(thisuser && thisuser.value!==undefined){ 
    
  for(let i=0;  i<thisuser.value.categories.length; i++){
    console.log("ok");
    
      sum += thisuser.value.categories[i].limitamount;
    
  }
}

console.log(sum);
return sum;
}
const handleClick = () => {
  // setBalance(newBalance);
  // INSERT ALERT WITH FORM TO CHANGE BALANCE

  console.log('Clicked on Geplante monatliche Ausgaben');
}

</script>

<template>
  <ion-page>
    <ion-content class="ion-padding">
      <ion-header>
        <ion-toolbar>
          <ion-title>Profile</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid v-if="thisuser">
        <ion-row>
          <ion-col>
            <ion-item>
              <ion-label>Benutzer:</ion-label>
              <ion-text>{{ thisuser.loginName }}</ion-text>
            </ion-item>
          </ion-col>
        </ion-row>
      </ion-grid>
      <ion-grid v-else>
        <ion-row>
          <ion-col>
            <ion-item>
              <ion-label>Daten werden geladen</ion-label>
            </ion-item>
          </ion-col>
        </ion-row>
      </ion-grid>
      <ion-button @click="logout()">Logout</ion-button>
    </ion-content>
  </ion-page>
</template>
  
<style scoped>

</style>
