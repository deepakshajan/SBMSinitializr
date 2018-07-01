import React from "react";

class HeaderHeading extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var contentStyle={position: 'absolute',top: 0,bottom: 0,left: 0,right: 0,width: '50%',height: '30%',margin: 'auto'};

        return(
            <div className='header-heading-container' style={this.props.style}>
                <div className='header-heading-content' style={contentStyle}>{this.props.heading}</div>
            </div>
        );
    }
}

export default HeaderHeading;