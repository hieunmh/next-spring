import { UserType } from '@/types/type';
import { create } from 'zustand';

interface useConnectedUserType {
  connectedUser: UserType[],
  setConnectedUser: (users: UserType[]) => void;
}

export const useConnectedUser = create<useConnectedUserType>(set => ({
  connectedUser: [],
  setConnectedUser: (users: UserType[]) => set({ connectedUser: users })
}))