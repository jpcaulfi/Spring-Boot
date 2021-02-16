import './App.css';
import TopDriversComponent from "./components/TopDriversComponent";
import BottomDriversComponent from "./components/BottomDriversComponent";
import HeaderComponent from './components/HeaderComponent';
import RoutesAtRiskComponent from './components/RoutesAtRiskComponent';

function App() {
  return (
    <div className="App">
      <HeaderComponent />
      <RoutesAtRiskComponent />
      <TopDriversComponent />
      <BottomDriversComponent />
    </div>
  );
}

export default App;
