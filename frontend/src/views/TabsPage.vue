<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="todo" href="/tabs/dashboard">
          <ion-icon :icon="homeSharp" />
          <ion-label>Dashboard</ion-label>
        </ion-tab-button>
          
        <ion-tab-button tab="projects" href="/tabs/categories">
          <ion-icon :icon="fileTrayFullSharp" />
          <ion-label>Category</ion-label>
        </ion-tab-button>
        
        <ion-tab-button tab="timerecord" href="/tabs/user">
          <ion-icon :icon="personCircleSharp" />
          <ion-label>Profile</ion-label>
        </ion-tab-button>
      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script setup lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage, IonRouterOutlet,  } from '@ionic/vue';
import { flash, apps, send, fileTrayFullSharp, homeSharp, personCircleSharp } from 'ionicons/icons';
</script>