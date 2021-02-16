import './App.css';
import AllDriversComponent from "./components/AllDriversComponent";
import HeaderComponent from './components/HeaderComponent';

function SeeAll() {
  return (
    <div className="App">
      <HeaderComponent />
      <AllDriversComponent />
    </div>
  );
}

export default SeeAll;
