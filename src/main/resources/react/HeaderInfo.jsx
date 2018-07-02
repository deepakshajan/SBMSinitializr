import React from "react";
import PropTypes from "prop-types";

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

HeaderInfo.propTypes = {style: PropTypes.object};

export default HeaderInfo;