import React from "react";
import HeaderInfo from "./HeaderInfo.jsx";
import HeaderHeading from "./HeaderHeading.jsx";
import PropTypes from "prop-types";

class Header extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var headerInfoStyle = {display:'inline-block',height:'inherit',width:'85%'};
        var headerHeadingStyle = {display:'inline-block',fontcolor:'green',height:'inherit',fontWeight:'bolder',color:'white',position:'relative',width:'15%'};

        return(
            <div className='header-container' style={this.props.style}>
                <HeaderInfo style={headerInfoStyle}/>
                <HeaderHeading heading={this.props.heading} style={headerHeadingStyle}/>
            </div>
        );
    }
}

Header.propTypes = {
    style: PropTypes.object
}


export default Header;