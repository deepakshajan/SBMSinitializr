import React from "react";
import PropTypes from "prop-types";

class ProcessPanelStatus extends React.Component {
    
    constructor(props) {
        super(props);

    }

    render() {
        let innerContainerStyle = {};
        let spanStyle = {color:this.props.colour};

        return(
            <div className='process-panel-status-container' style={this.props.style}>
                <div className='process-panel-status-inner-container' style={innerContainerStyle}>
                    <span className='process-panel-status-span' style={spanStyle}>{this.props.value+"..."}</span>
                </div>
            </div>
        );
    }

}

ProcessPanelStatus.propTypes = {value: PropTypes.string, colour: PropTypes.string};

ProcessPanelStatus.defaultProps = {value : "DefaultValue", colour:'white'};

export default ProcessPanelStatus;