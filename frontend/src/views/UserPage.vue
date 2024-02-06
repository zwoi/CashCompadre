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
} from "@ionic/vue";
import { useTodos } from "../composables/useTodos";
import { ref } from "vue";
const text = ref('!');
const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } = useTodos();

</script>

<template>
  <ion-page>
    <ion-content>
      <ion-header>
        <ion-toolbar>
          <ion-title>Profile</ion-title>
        </ion-toolbar>
      </ion-header>
      <p>Was  geht </p>
    </ion-content>
  </ion-page>
</template>
  
