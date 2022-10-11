import { BrowserRouter, Routes, Route } from "react-router-dom";
import Listing from 'pages/Listing';
import Post from 'pages/Post';
import Navbar from "components/Navbar";


function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Listing />} />
        <Route path="/post">
          <Route path=":postId" element={<Post />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
