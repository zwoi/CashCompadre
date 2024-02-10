import { toastController } from '@ionic/vue';

export const showToast = async (message, duration = 2000) => {
  const toast = await toastController.create({
    message: message,
    duration: duration,
    position: 'bottom',
    color: 'primary',
  });
  toast.present();
};