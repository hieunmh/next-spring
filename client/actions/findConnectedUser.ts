import { UserType } from '@/types/type';
import { useConnectedUser } from '@/hooks/useConnectedUser';

export const findConnectedUser = async (nickname: string) => {
  const { connectedUser, setConnectedUser } = useConnectedUser();
  const res = await fetch('http://localhost:8088/ws/users');
  let connected: UserType[] = await res.json();

  setConnectedUser(connected.filter(user => user.nickname !== nickname));

}
