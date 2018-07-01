import React from "react";
import HeaderInfo from "./HeaderInfo.jsx";
import HeaderHeading from "./HeaderHeading.jsx";

class Header extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var headerInfoStyle = {display:'inline-block',height:'inherit',width:'85%'};
        var headerHeadingStyle = {display:'inline-block',float:'right',fontcolor:'green',height:'inherit',fontWeight:'bolder',color:'white',position:'relative',width:'15%'};

        return(
            <div className='header-container' style={this.props.style}>
                <HeaderInfo style={headerInfoStyle}/>
                <HeaderHeading heading={this.props.heading} style={headerHeadingStyle}/>
            </div>
        );
    }
}

export default Header;