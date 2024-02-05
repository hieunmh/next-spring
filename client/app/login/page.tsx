'use client'

import React, { useState } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

import axios from 'axios';
import { findConnectedUser } from '@/actions/findConnectedUser';

export default function Login() {
  
  let [nickname, setNickname] = useState<string>('');
  let [fullname, setFullname] = useState<string>('');

  const socket = new SockJS('http://localhost:8088/ws');

  let stompClient = Stomp.over(socket);

  const login = async () => {
    console.log(nickname, fullname);
    if (!nickname || !fullname) {
      return;
    }

    stompClient.connect({}, onConnected, onError);
    
  }

  const onConnected = () => {
    stompClient.subscribe(`user/${nickname}/queue/messages`, onMSgReceive);
    stompClient.subscribe(`user/public`, onMSgReceive);

    // register the connect user
    stompClient.send(
      '/app/user.addUser', 
      {},
      JSON.stringify({
        nickname: nickname,
        fullname: fullname,
        status: 'ONLINE'
      })
    );

    //find a display user
    findConnectedUser(nickname);
  }



  const onError = () => {}

  const onMSgReceive = () => {

  }

  return (
    <div className='w-screen h-screen fixed flex items-center justify-center'>
      <div className='w-[400px] h-fit bg-gray-50 rounded-md shadow-xl p-8 space-y-10'>

        <p className=' text-3xl font-bold text-center'>Join chat room</p>

        <div>
          <p>Nick name</p>
          <input type='text' className='bg-gray-50 border-[1px] border-gray-300 
            rounded outline-none py-2 px-4 w-full font-semibold'
            onChange={(e) => setNickname(e.target.value)}
          />
        </div>

        <div>
          <p>Full name</p>
          <input type='text' className='bg-gray-50 border-[1px] border-gray-300 
            rounded outline-none py-2 px-4 w-full font-semibold'
            onChange={(e) => setFullname(e.target.value)}
          />
        </div>

        <div>
          <button className='w-full bg-[#5c9ead] py-2 rounded font-semibold'
            onClick={login}
          >
            Join chat
          </button>
        </div>
      </div>
    </div>
  )
}
