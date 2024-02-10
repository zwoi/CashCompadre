<script setup lang="ts">

import {
  ref,
  IonModal,
  IonItem,
  IonInput,
  IonButton,
  IonHeader,
  IonToolbar,
  IonButtons,
  IonTitle,
} from '@ionic/vue';
import { Expense } from '@/model/expense';
import { useExpenses } from './useExpenses';
import { useCategory } from '../composables/useCategory';

export function addExpenseComp() {
  const { getExpenses, addExpense } = useExpenses();
  const { categories } = useCategory();

  const message = ref('This modal example uses triggers to automatically open a modal when the button is clicked.');
  const modal = ref<IonModal>();
  const input = ref<IonInput>();

  const cancel = () => modal.value.hide();

  const confirm = () => {
    const name = input.value.value;
    modal.value.hide();
    // Your confirm logic here
  };

  return { message, modal, input, cancel, confirm };
}


</script>

<template>
    <ion-header>
      <ion-toolbar>
        <ion-title>Inline Modal</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <ion-button id="open-modal" expand="block">Open</ion-button>
      <p>{{ message }}</p>
      <ion-modal ref="modal" trigger="open-modal" @willDismiss="onWillDismiss">
        <ion-header>
          <ion-toolbar>
            <ion-buttons slot="start">
              <ion-button @click="cancel()">Cancel</ion-button>
            </ion-buttons>
            <ion-title>Welcome</ion-title>
            <ion-buttons slot="end">
              <ion-button :strong="true" @click="confirm()">Confirm</ion-button>
            </ion-buttons>
          </ion-toolbar>
        </ion-header>
        <ion-content class="ion-padding">
          <ion-item>
            <ion-input
              label="Enter your name"
              label-placement="stacked"
              ref="input"
              type="text"
              placeholder="Your name"
            ></ion-input>
          </ion-item>

          
      
      


        </ion-content>
      </ion-modal>
    </ion-content>
  </template>