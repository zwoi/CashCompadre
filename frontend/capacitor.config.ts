import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'io.ionic.starter',
  appName: 'frontend',
  webDir: 'dist',
  server: {
    androidScheme: 'https'
  }, plugins: {
    LocalNotifications: {
      
      iconColor: "#488AFF",
      
    },
  },
};

export default config;
