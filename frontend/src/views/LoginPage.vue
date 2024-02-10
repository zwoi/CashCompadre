<template>
    <ion-page>
      <ion-content :fullscreen="true" padding>
        <ion-grid>
          <ion-row justify-content-center>
            <ion-col align-self-center size-md="6" size-lg="5" size-xs="12">
              <ion-col>
              
                <ion-card>
                
                  <ion-img src="/images/logo.png" class="custom-image"></ion-img>
                </ion-card>
                </ion-col>
              <div>
                <center>
                <h1 class="title"> Handle your finances with ease!</h1>
              </center>
              </div>
              <div>
                <div class="">
                <ion-item lines="none">
                  <ion-input  type="text" placeholder="Login Name" v-model="username"></ion-input>
                </ion-item>
              </div>
                <ion-item lines="none">
                  <ion-input color="dark" placeholder="Password" v-model="password"></ion-input>
                </ion-item>
              </div>
              <div>
                <ion-button size="large" @click="loginnot" expand="block">Login</ion-button>
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
  IonImg,
  IonCard
} from "@ionic/vue";
import { useLogin } from "../composables/useLogin";
import { body, notifications, options } from "ionicons/icons";
/* import { LocalNotifications, ScheduleOptions } from "@capacitor/local-notifications";
 */function loginnot(){
  /* let options:ScheduleOptions={
    notifications: [{
      id:111,
      title: "Hello",
      body: "Thanks for logging in",  
      largeBody: "Add your first Item!",
  }],
}
    LocalNotifications.schedule(options);
  */
  login();
}
const { username, password, login } = useLogin();

</script>

<style>
.title{
  color: white;
  font-style: oblique;
}
.logo{
  width: 100%;
  max-width: 200px;
  margin: 0 auto;
  display: block;
  padding: 20px 0;
}
ion-item{
  border-radius: 5px;
  color: #f6f8e2;
  outline-style: solid;
  outline-width: 0.5px;
  box-shadow: 0 !important;
  margin-bottom: 10px;
  margin-top: 10px;
  
}

ion-image{
  width: 100%;
  height: 100%;
}
ion-card{
  border-radius: 5px;
  color: #f6f8e2;
  outline-style: solid;
  outline-width: 0.5px;
  box-shadow: 0 !important;
  margin-top: 20px;
  margin-bottom: 20px;
}

 


</style>