import React, { useState } from 'react';
import {
  MDBContainer,
  MDBNavbar,
  MDBNavbarBrand,
  MDBNavbarToggler,
  MDBNavbarNav,
  MDBNavbarLink,
  MDBIcon,
  MDBCollapse,
  MDBNavbarItem
} from 'mdb-react-ui-kit';
import { useNavigate } from "react-router-dom";

function Navbar() {
  const [showNavSecond, setShowNavSecond] = useState(false);
  let navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("jwt");
    navigate("/");
  }
  
  return (
    <MDBNavbar expand='lg' light bgColor='light'>
      <MDBContainer fluid>
        <MDBNavbarBrand >My Taylor</MDBNavbarBrand>
        <div class="d-flex input-group w-auto">
            <MDBNavbarLink onClick={logout}>Logout</MDBNavbarLink>
        </div>
      </MDBContainer>
    </MDBNavbar>
  );
}

export default Navbar;