import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import AppRouter from "./router/AppRouter"

function App() {
  return (
    <div className='overflow-x-hidden'>
      <AppRouter/>
    </div>
  )
}

export default App
