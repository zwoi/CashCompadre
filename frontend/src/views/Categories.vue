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
const text = ref('hello');
const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } = useTodos();
import {add} from 'ionicons/icons'; 
</script>

<template>
  <h1>test</h1>
    <IonButton>{{ text }}</IonButton>
    <ion-fab>
    <ion-fab-button>
      <ion-icon :icon="add"></ion-icon>
    </ion-fab-button>
  </ion-fab>
</template>
  
