import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './auth';
import NavBar from './components/NavBar/NavBar';
import Home from './components/Home/Home';
import Login from './components/Login/Login';
import SignUp from './components/SignUp/SignUp';
import Landing from './components/Landing/Landing';

function App() {
    return (
        <Router>
            <AuthProvider>
                <NavBar />
                <main className="container">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/signup" element={<SignUp />} />
                        <Route path="/landing" element={<Landing />} />
                    </Routes>
                </main>
            </AuthProvider>
        </Router>
    );
}

export default App;
