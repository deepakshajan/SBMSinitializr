import React from "react";
import PropTypes from "prop-types";

class HeaderHeading extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var tableStyle = {display: 'table', height: 'inherit', width: '100%'};
        var contentStyle={display: 'table-cell', verticalAlign: 'middle', textAlign: 'center'};

        return(
            <div className='header-heading-container' style={this.props.style}>
                <div className='header-heading-table' style={tableStyle}>
                    <div className='header-heading-content' style={contentStyle}>{this.props.heading}</div>
                </div>
            </div>
        );
    }
}

HeaderHeading.defaultProps = {heading:'Heading'};

HeaderHeading.propTypes = {style:PropTypes.object,
    heading:PropTypes.string};

export default HeaderHeading;