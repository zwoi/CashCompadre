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
    <IonButton>{{ text }}</IonButton>
</template>
  
