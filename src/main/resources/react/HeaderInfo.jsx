import React from "react";

class HeaderInfo extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div className='header-info-container' style={this.props.style}>
                {/* Any info html if present should go here */}
            </div>
        );
    }
}

export default HeaderInfo;