
import './App.css';
import { Routes, Route, useHistory} from "react-router-dom";
import { useEffect, useState} from "react";
import Navbar from "./components/Navbar";
import ConfessionList from "./components/ConfessionList";
import ConfessionService from "./repository/repository";


function App() {
    const sampleConfessions = [
        { id: 1, text: "This is a confession.", likes: 10, dislikes: 2 },
        { id: 2, text: "Another secret revealed.", likes: 5, dislikes: 1 }
    ];




  return (

    <div className="App">
      <Navbar />

        <Routes>
            <Route  path="/popular" element={<ConfessionList type="popular" />} />
            <Route  path="/"  element={<ConfessionList type="all" />} />

        </Routes>
    </div>
  );
}

export default App;
