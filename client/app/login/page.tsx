import React from 'react'

export default function Login() {
  return (
    <div className='w-screen h-screen fixed flex items-center justify-center'>
      <div className='w-[400px] h-fit bg-gray-50 rounded-md shadow-xl p-8 space-y-10'>

        <p className=' text-3xl font-bold text-center'>Join chat room</p>

        <div>
          <p>Nick name</p>
          <input type='text' className='bg-gray-50 border-[1px] border-gray-300 
            rounded outline-none py-2 px-4 w-full font-semibold'
          />
        </div>

        <div>
          <p>Full name</p>
          <input type='text' className='bg-gray-50 border-[1px] border-gray-300 
            rounded outline-none py-2 px-4 w-full font-semibold'
          />
        </div>

        <div>
          <button className='w-full bg-[#5c9ead] py-2 rounded font-semibold'>
            Join chat
          </button>
        </div>
      </div>
    </div>
  )
}
