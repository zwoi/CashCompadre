<template>
    <ion-page>
      <ion-content :fullscreen="true" padding>
        <ion-grid>
          <ion-row justify-content-center>
            <ion-col align-self-center size-md="6" size-lg="5" size-xs="12">
              <ion-col>
                <img class="logo" src="/images/logo.png" alt="LeanTodo logo" />
              </ion-col>
              <div>
                <h1 class="title">LeanTodo login</h1>
              </div>
              <div>
                <div class="">
                <ion-item>
                  <ion-input type="text" placeholder="Login Name" v-model="username"></ion-input>
                </ion-item>
              </div>
                <ion-item>
                  <ion-input type="password" placeholder="Password" v-model="password"></ion-input>
                </ion-item>
              </div>
              <div>
                <ion-button size="large" @click="login" expand="block">Login</ion-button>
              </div>
            </ion-col>
          </ion-row>
        </ion-grid>
      </ion-content>
    </ion-page>

</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent,
  IonInput,
  IonRow,
  IonCol,
  IonGrid,
  IonButton,
  IonItem,
} from "@ionic/vue";
import { useLogin } from "../composables/useLogin";

const { username, password, login } = useLogin();

</script>

<style>
.title{
  color: white;
}
.logo{
  width: 100%;
  max-width: 200px;
  margin: 0 auto;
  display: block;
  padding: 20px 0;
}
ion-item{
  --border-radius: 5px;
  padding-bottom: 10px;
}
.img{
  border-radius: 5px;
}



</style>