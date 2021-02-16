import './App.css';
import TopDriversComponent from "./components/TopDriversComponent";
import BottomDriversComponent from "./components/BottomDriversComponent";
import HeaderComponent from './components/HeaderComponent';
import RoutesAtRiskComponent from './components/RoutesAtRiskComponent';
import RescuesComponent from './components/RescuesComponent';
import BidsComponent from './components/BidsComponent';
import 'bootstrap/dist/css/bootstrap.css';

function App() {
  return (
    <div className="App">
      <HeaderComponent />
      <RoutesAtRiskComponent />
      <div className="rescues-master-div">
        <RescuesComponent/>
        <BidsComponent/>
      </div>
      <TopDriversComponent />
      <BottomDriversComponent />
    </div>
  );
}

export default App;
